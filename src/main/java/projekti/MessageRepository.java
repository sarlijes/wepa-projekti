package projekti;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageRepository extends JpaRepository<Message, Long> {

    Page<Message> findMax25ByRecieverId(Pageable pageable, Long id);
    List<Message> findAllByRecieverId(Long id);
}
