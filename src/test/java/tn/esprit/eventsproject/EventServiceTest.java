package tn.esprit.eventsproject;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Logistics;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.entities.Tache;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.LogisticsRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;
import tn.esprit.eventsproject.services.EventServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EventServiceTest {

  @Mock
  private EventRepository eventRepository;

  @Mock
  private ParticipantRepository participantRepository;

  @Mock
  private LogisticsRepository logisticsRepository;

  @Autowired
  private EventServicesImpl eventsService;

  @Test
  void testCreateParticipant() {
    int id = 1;
    Participant expectedParticipant = new Participant();
    expectedParticipant.setIdPart(id);

    when(participantRepository.save(Mockito.any(Participant.class))).thenReturn(expectedParticipant);

    Participant actualParticipant = eventsService.addParticipant(expectedParticipant);

      assertEquals(expectedParticipant.getIdPart(), actualParticipant.getIdPart());
  }

  @Test
  void addAffectEvenParticipant() {
    Participant expectedParticipant = Participant.builder()
        .nom("mourad")
        .tache(Tache.ORGANISATEUR)
        .prenom("slim")
        .idPart(1234)
        .isOverBurdened(true)
        .build();

    when(participantRepository.save(Mockito.any(Participant.class))).thenReturn(expectedParticipant);
    when(participantRepository.findById(1234)).thenReturn(Optional.ofNullable(expectedParticipant));
    Event e = eventsService.addAffectEvenParticipant(new Event(), 1234);
    assertNull(e);
  }
}

