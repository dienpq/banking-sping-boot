package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.entities.CommitmentContent;

public interface CommitmentContentRepository extends CrudRepository<CommitmentContent, Long> {

}
