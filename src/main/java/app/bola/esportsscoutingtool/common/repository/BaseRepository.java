package app.bola.esportsscoutingtool.common.repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<ENT, ID> extends JpaRepository<ENT, ID> {

}
