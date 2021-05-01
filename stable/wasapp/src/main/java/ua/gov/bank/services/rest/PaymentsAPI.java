package ua.gov.bank.services.rest;

import ua.gov.bank.model.Payment;
import ua.gov.bank.services.PaymentService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentsAPI {

    @Inject
    private PaymentService service;

    @GET
    public List<Payment> all() {
        return service.all();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") Integer id) {
        Payment payment = service.find(id);

        if (payment != null) {
            return Response.ok().entity(payment).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response create(Payment payment) {
        service.create(payment);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, Payment payment) {
        boolean paymentExists = service.find(id) != null;

        if (paymentExists) {
            payment.setId(id);
            service.update(payment);
            return Response.status(Response.Status.ACCEPTED).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        Payment payment = service.find(id);

        if (payment != null) {
            service.delete(payment);
            return Response.ok("Deleted.").build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
