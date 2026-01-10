package app.bola.esportsscoutingtool.common.repository;
import app.bola.esportsscoutingtool.common.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<ENT extends BaseModel, ID> extends JpaRepository<ENT, ID> {

}
