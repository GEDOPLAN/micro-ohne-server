package de.gedoplan.micro.persistence;

import de.gedoplan.micro.entity.Textblock;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
// public class TextblockRepository extends SingleIdEntityRepository<Long, Textblock> {
public class TextblockRepository {

  private ConcurrentMap<Long, Textblock> store = new ConcurrentHashMap<>();
  private AtomicLong idGenerator = new AtomicLong();

  public void persist(Textblock textblock) {
    textblock.setId(this.idGenerator.incrementAndGet());
    this.store.put(textblock.getId(), textblock);
  }

  public void removeById(Long id) {
    this.store.remove(id);
  }

  public Textblock findById(Long id) {
    return this.store.get(id);
  }

  public Collection<Textblock> findAll() {
    return this.store.values();
  }

  public int countAll() {
    return this.store.size();
  }

}
