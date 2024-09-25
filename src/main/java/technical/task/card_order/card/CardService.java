package technical.task.card_order.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import technical.task.card_order.domain.CardDTO;
import technical.task.card_order.domain.CardProjection;

import java.util.List;

import static technical.task.card_order.card.Converter.toCardDTOS;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Service
class CardService {
    private static final Logger logger = LoggerFactory.getLogger(CardService.class);
    private final CardRepository repository;

    CardService(CardRepository repository) {
        this.repository = repository;
    }

    public List<CardDTO> getCards(int from, int limit) {
        logger.info("Getting cards, from: {}, limit: {}", from, limit);
        List<CardProjection> projections = repository.getCards(from, limit);
        List<CardDTO> cards = toCardDTOS(projections);
        logger.info("Retrieved cards: {}", cards.size());
        return cards;
    }
}
