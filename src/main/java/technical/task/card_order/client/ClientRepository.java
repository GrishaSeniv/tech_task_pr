package technical.task.card_order.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import technical.task.card_order.domain.model.ClientProjection;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Hryhorii Seniv
 * @version 2024-09-25
 */
@Repository
interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    @Query(
            value = """
                    SELECT c.id,
                           c.first_name as firstName,
                           c.last_name as lastName,
                           c.first_name_lat as firstNameLat,
                           c.last_name_lat as lastNameLat,
                           c.birthday,
                           c.phone,
                           c.email,
                           u.first_name as operatorFirstName,
                           u.last_name as operatorLastName,
                           u.surname as operatorSurname
                    FROM client c
                    INNER JOIN `user` u ON c.created_by = u.id
                    ORDER BY c.id DESC
                    LIMIT :limit
                    OFFSET :offset
                    """, nativeQuery = true)
    List<ClientProjection> getClients(@Param("offset") int offset,
                                      @Param("limit") int limit);

    @Query(
            value = """
                    SELECT c.id,
                           c.first_name as firstName,
                           c.last_name as lastName,
                           c.first_name_lat as firstNameLat,
                           c.last_name_lat as lastNameLat,
                           c.birthday,
                           c.phone,
                           c.email,
                           u.first_name as operatorFirstName,
                           u.last_name as operatorLastName,
                           u.surname as operatorSurname
                    FROM client c
                    INNER JOIN `user` u ON c.created_by = u.id
                    WHERE c.id = :clientId
                    """, nativeQuery = true)
    ClientProjection getClient(@Param("clientId") Long clientId);

    @Modifying
    @Query(value = """
            UPDATE client
            SET first_name = :firstName,
                last_name = :lastName,
                first_name_lat = :firstNameLat,
                last_name_lat = :lastNameLat,
                birthday = :birthday,
                phone = :phone,
                email = :email
            WHERE id = :clientId
            """, nativeQuery = true)
    int updateClient(@Param("firstName") String firstName,
                     @Param("lastName") String lastName,
                     @Param("firstNameLat") String firstNameLat,
                     @Param("lastNameLat") String lastNameLat,
                     @Param("birthday") LocalDate birthday,
                     @Param("phone") String phone,
                     @Param("email") String email,
                     @Param("clientId") Long clientId);
}
