package user.service.kafka.project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import user.service.MemberService;
import user.service.web.dto.member.request.MemberMappingToProjectRequestDto;
import user.service.kafka.project.event.CreateMemberAtProjectEvent;
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProjectConsumerService {

}
