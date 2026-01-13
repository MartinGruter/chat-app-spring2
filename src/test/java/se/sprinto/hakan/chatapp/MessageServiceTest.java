package se.sprinto.hakan.chatapp;

import com.mysql.cj.Messages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sprinto.hakan.chatapp.model.Message;
import se.sprinto.hakan.chatapp.repository.MessageRepository;
import se.sprinto.hakan.chatapp.service.MessageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageService messageService;


    @Test
    void testSave(){
        Message message = new Message();
        message.setText("Test");

        messageRepository.save(message);

        assertEquals("Test", message.getText());
        verify(messageRepository).save(message);
    }

    @Test
    void testGetMessages(){
        Message message1 = new Message();
        message1.setText("Hej");
        Message message2 = new Message();
        message2.setText("Hej igen");
        List<Message> fakeList = Arrays.asList(message1, message2);
        when(messageRepository.findByUserId(1L)).thenReturn(fakeList);

        List<Message> result = messageService.getMessages(1L);

        assertEquals(2, result.size());
        assertEquals("Hej", result.get(0).getText());
        assertEquals("Hej igen", result.get(1).getText());
    }
}
