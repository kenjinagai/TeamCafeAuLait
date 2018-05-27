package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.ExtendedAuthentication;

/**
 * ExtendedAuthenticationRepository
 *
 * @author Kenji Nagai
 *
 */
@Repository
public interface ExtendedAuthenticationRepository
        extends JpaRepository<ExtendedAuthentication, String> {
    /**
     * Find with extended_authentication_value.
     *
     * @param itemPrefix extended_authentication_value prefix
     * @return ExtendedAuthentication
     */
    public ExtendedAuthentication findByExtendedAuthenticationValueIs(String itemPrefix);
}
