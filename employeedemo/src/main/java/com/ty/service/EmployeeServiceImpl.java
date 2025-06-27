package com.ty.service;

import com.ty.client.LeaveClient;
import com.ty.client.NotificationClient;
import com.ty.dto.LeaveRequestDto;
import com.ty.dto.SuccessResponse;
import com.ty.entity.Employee;
import com.ty.repository.EmployeeRepository;
import com.ty.dto.EmployeeRequest;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;
    private final NotificationClient notificationClient;
    private final LeaveClient leaveServiceClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    public Integer registerEmployee(EmployeeRequest request) throws RuntimeException{
        Employee employee = Employee.builder()
                            .firstname(request.getFirstname())
                            .lastname(request.getLastname())
                            .email(request.getEmail())
                            .build();

        Integer id = employeeRepository.save(employee).getId();
        String senderEmail = "hr@company.com";
        String receiverEmail = employee.getEmail();

//        SuccessResponse successResponse = restTemplate.postForObject("http://NOTIFICATIONMODULE/api/v1/notification/send-email?sender_email={sender}&receiver_email={receiver}", null, SuccessResponse.class, senderEmail, receiverEmail);
        SuccessResponse successResponse_ = notificationClient.sendEmail("hr@company.com", employee.getEmail());
        assert successResponse_ != null;
        System.out.println(successResponse_.getData());
        return id;
    }

    @Override
    public Object sendLeaveRequest(int id, LeaveRequestDto dto) {
        Employee employee=employeeRepository.findById(id).get();
        System.out.println(employee.getEmail());
        log.warn(employee.getEmail());
        String senderEmail = "hr@company.com";
        String receiverEmail = employee.getEmail();
        dto.setEmployeeId(id); // set employee ID
        Object obj=leaveServiceClient.applyLeave(dto).getData();
        String message="";
        if(Objects.nonNull(obj))message = "Leave applied by Employee ID: " + id;
        else message= "Leave not applied by Employee ID: " + id;
        System.out.println(message);
        log.warn(message);
        CompletableFuture<SendResult<String, String>> future=kafkaTemplate.send("leave-events", senderEmail+","+receiverEmail+","+message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("✅ Kafka message sent: " + result.getRecordMetadata());
                log.warn("✅ Kafka message sent: " + result.getRecordMetadata());
            } else {
                System.err.println("✅ Kafka message sent: " + result.getRecordMetadata());
                log.warn("✅ Kafka message sent: " + result.getRecordMetadata());
                ex.printStackTrace();
            }
        });
        return obj;
    }

//    @CircuitBreaker(name = "fetchEmployeeR4J", fallbackMethod = "fetchFallback")
//    @RateLimiter(name = "fetchEmployeeR4J", fallbackMethod = "fetchFallback")
//    @Retry(name = "fetchEmployeeR4J", fallbackMethod = "fetchFallback")
//    @TimeLimiter(name = "fetchEmployeeR4J", fallbackMethod = "fetchFallback")
//    @Bulkhead(name = "fetchEmployeeR4J", type = Bulkhead.Type.SEMAPHORE,fallbackMethod = "fetchFallback")
    @Override
    public Object fetchLeavesOfEmployee(int id) throws InterruptedException {
//            return CompletableFuture.supplyAsync(() -> {
//                try {
//                    Thread.sleep(10000); // Simulate long call
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                return leaveServiceClient.getLeavesByEmployee(id).getData();
//            });
        Thread.sleep(5000);
            return leaveServiceClient.getLeavesByEmployee(id).getData();
    }

    public Object fetchFallback(int id, Exception exception) {
        System.out.println("Fallback activated: " + exception.getMessage());
//        return CompletableFuture.completedFuture("Employee fetch failed for id "+id);
        return "Employee fetch failed for id "+id;
    }
}
