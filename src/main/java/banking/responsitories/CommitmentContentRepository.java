package banking.responsitories;

import org.springframework.data.repository.CrudRepository;

import banking.models.CommitmentContent;

public interface CommitmentContentRepository extends CrudRepository<CommitmentContent, Long> {

}
