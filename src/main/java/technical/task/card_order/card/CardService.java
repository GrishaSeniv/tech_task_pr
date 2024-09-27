package technical.task.card_order.card;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import technical.task.card_order.domain.model.CardDTO;
import technical.task.card_order.domain.model.CardProjection;
import technical.task.card_order.domain.model.CreateCardRequest;
import technical.task.card_order.domain.model.UpdateCardRequest;
import technical.task.card_order.user.UserInfoDetails;

import java.util.List;

import static technical.task.card_order.card.Converter.toCardDTO;
import static technical.task.card_order.card.Converter.toCardDTOS;
import static technical.task.card_order.card.Converter.toCardEntity;

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

    public List<CardDTO> getCards(int from, int limit, Boolean isActive) {
        logger.info("Getting cards, from: {}, limit: {}", from, limit);
        List<CardProjection> projections = repository.getCards(from, limit, isActive == null,
                isActive != null && isActive);
        List<CardDTO> cards = toCardDTOS(projections);
        logger.info("Retrieved cards: {}", cards.size());
        return cards;
    }

    public CardDTO getCard(Long id) {
        logger.info("Getting card by id: {}", id);
        CardProjection projection = repository.getCard(id);
        if (projection == null) {
            String msg = String.format("Card with id: %s not found", id);
            logger.error(msg);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
        }
        CardDTO card = toCardDTO(projection);
        logger.info("Retrieved card: {}", card);
        return card;
    }

    public CardDTO addCard(CreateCardRequest request, UserInfoDetails operator) {
        logger.info("Adding card: {}", request);
        CardEntity entity = toCardEntity(request, operator.getId());
        repository.save(entity);
        logger.info("Card added: {}", entity);
        return toCardDTO(entity, operator);
    }

    @Transactional
    public CardDTO updateCard(Long cardId, UpdateCardRequest request, UserInfoDetails userInfoDetails) {
        logger.info("Updating card: {}", request);
        repository.updateCard(request.getName(), request.getBin(), request.getIsActive(), cardId, userInfoDetails.getId());
        CardDTO card = toCardDTO(request, cardId);
        logger.info("Card updated: {}", card);
        return card;
    }
}
