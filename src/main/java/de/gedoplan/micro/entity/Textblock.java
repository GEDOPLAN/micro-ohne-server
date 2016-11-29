package de.gedoplan.micro.entity;

import de.gedoplan.baselibs.persistence.entity.GeneratedLongIdEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Textblock extends GeneratedLongIdEntity {
  @NotNull
  @Size(min = 1)
  private String value;

  public Textblock(String value) {
    this.value = value;
  }

  protected Textblock() {
  }
}
