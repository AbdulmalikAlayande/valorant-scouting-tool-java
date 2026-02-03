package app.bola.esportsscoutingtool.common.repository;
import app.bola.esportsscoutingtool.common.model.BaseModel;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<ENT extends BaseModel, ID extends Serializable> extends JpaRepository<@NonNull ENT, @NonNull ID> {

	Optional<ENT> findByPublicId(String publicId);
}
