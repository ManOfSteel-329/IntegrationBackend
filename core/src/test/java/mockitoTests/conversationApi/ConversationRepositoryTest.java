package mockitoTests.conversationApi;
import com.funnelsensai.core.domain.Conversation;
import com.funnelsensai.core.repository.ConversationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ConversationRepositoryTest {

    private ConversationRepository conversationRepository;

    public ConversationRepositoryTest(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Test
    void testSaveAndFindConversation() {
        // Create and save a Conversation entity
        Conversation conversation = new Conversation();
        conversation.setContactId("12345");
        conversation.setLocationId("67890");
        conversation.setDeleted(false);
        conversation.setInbox(true);
        conversation.setType(1.0f);
        conversation.setUnreadCount(3.0f);
        conversation.setAssignedTo("user@example.com");
        conversation.setConversationId("conv-001");
        conversation.setStarred("true");

        Conversation savedConversation = conversationRepository.save(conversation);

        // Fetch from database
        Optional<Conversation> found = conversationRepository.findById(savedConversation.getId());

        // Assertions
        assertThat(found).isPresent();
        assertThat(found.get().getContactId()).isEqualTo("12345");
        assertThat(found.get().getConversationId()).isEqualTo("conv-001");
    }
}
