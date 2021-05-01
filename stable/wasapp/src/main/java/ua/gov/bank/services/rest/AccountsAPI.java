package ua.gov.bank.services.rest;

import ua.gov.bank.model.Account;
import ua.gov.bank.services.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountsAPI {

    @Inject
    private AccountService service;

    @GET
    public List<Account> all() {
        return service.all();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        Account acc = service.find(id);

        if (acc == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(acc).build();
    }

    @GET
    @Path("owner/{id}")
    public Response findByOwner(@PathParam("id") Integer id) {
        List<Account> result = service.findByUser(id);

        return Response.ok(result).build();
    }

    @GET
    @Path("search")
    public Response findByUserName(@DefaultValue("") @QueryParam("firstname") String firstname,
                                   @DefaultValue("") @QueryParam("lastname") String lastname) {
        List<Account> result = service.findByUserName(firstname, lastname);

        return result.isEmpty()
                ? Response.status(Response.Status.NOT_FOUND).build()
                : Response.ok(result).build();
    }

    @POST
    public Response create(Account acc) {
        service.create(acc);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Account account) {
        boolean accountExist = service.find(id) != null;

        if (accountExist) {
            account.setId(id);
            service.update(account);

            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Account acc = service.find(id);

        if (acc != null) {
            service.delete(acc);
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
