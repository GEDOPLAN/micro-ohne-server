package de.gedoplan.micro.persistence;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.micro.entity.Textblock;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class TextblockRepository extends SingleIdEntityRepository<Long, Textblock>
{

}
