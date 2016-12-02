package de.gedoplan.micro.webservice;

import de.gedoplan.micro.entity.Textblock;
import de.gedoplan.micro.persistence.TextblockRepository;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path(TextblockResource.PATH)
public class TextblockResource {

  public static final String PATH = "text";

  public static final String ID_PARAM = "id";
  public static final String ID_TEMPLATE = "{" + ID_PARAM + "}";

  @Inject
  TextblockRepository textblockRepository;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public List<String> getAll() {

    return this.textblockRepository
        .findAll()
        .stream()
        .map(tb -> tb.getValue())
        .collect(Collectors.toList());
  }

  @GET
  @Path(ID_TEMPLATE)
  @Produces(MediaType.TEXT_PLAIN)
  public String get(@PathParam(ID_PARAM) Long id) {
    Textblock textblock = this.textblockRepository.findById(id);
    if (textblock != null) {
      return textblock.getValue();
    } else {
      throw new NotFoundException();
    }
  }

  @PUT
  @Path(ID_TEMPLATE)
  @Consumes(MediaType.TEXT_PLAIN)
  @Transactional(rollbackOn = Exception.class)
  public void put(@PathParam(ID_PARAM) Long id, String value) {
    Textblock textblock = this.textblockRepository.findById(id);
    if (textblock != null) {
      textblock.setValue(value);
    } else {
      throw new NotFoundException();
    }
  }

  @POST
  @Consumes(MediaType.TEXT_PLAIN)
  public Response post(String value, @Context UriInfo uriInfo) {
    Textblock textblock = new Textblock(value);
    this.textblockRepository.persist(textblock);

    URI uri = uriInfo.getAbsolutePathBuilder()
        .path(ID_TEMPLATE)
        .resolveTemplate(ID_PARAM, textblock.getId())
        .build();
    return Response.created(uri).build();
  }

  @DELETE
  @Path(ID_TEMPLATE)
  public void delete(@PathParam(ID_PARAM) Long id) {
    this.textblockRepository.removeById(id);
  }
}
