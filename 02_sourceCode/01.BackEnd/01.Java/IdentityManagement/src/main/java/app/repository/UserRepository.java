package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.User;

/**
 * User Repository for JPA.
 *
 * @author Kenji Nagai.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public User findByExtendedAuthenticationIdIs(Integer extendedAuthenticationId);

}
