package app.bola.esportsscoutingtool.common.repository;
import app.bola.esportsscoutingtool.common.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<ENT extends BaseModel, ID extends Serializable> extends JpaRepository<ENT, ID> {

}
