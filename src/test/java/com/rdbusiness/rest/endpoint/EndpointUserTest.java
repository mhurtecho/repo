package com.rdbusiness.rest.endpoint;

import com.rdbusiness.rest.bean.BeanUser;
import com.rdbusiness.rest.endpoint.EndpointTest.crud;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.*;

public class EndpointUserTest {

    private Entity<BeanUser> entity;
    private Response response;
    private EndpointTest endpoint;
    private BeanUser result;


    public void testCRUD() {
        create();
        get(crud.CREATE);
        update();
        get(crud.UPDATE);
        delete();
        get(crud.DELETE);
        list();
    }

    private void create() {
        entity = Entity.entity(getEntityToCreate(), MediaType.APPLICATION_JSON);
        response = endpoint.target(crud.CREATE, "user/add", entity);

        assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());

        result = response.readEntity(BeanUser.class);

        checkBean(user, true);

        user.setId(result.getId());
        user.getAudit().setCreateTime(result.getAudit().getCreateTime());
    }

    private void update() {
        entity = Entity.entity(getEntityToUpdate(), MediaType.APPLICATION_JSON);
        response = endpoint.target(crud.UPDATE, "user/" + user.getId(), entity);

        assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());

        result = response.readEntity(BeanUser.class);

        checkBean(user, false);

        user.getAudit().setModifyTime(result.getAudit().getModifyTime());
    }

    private void delete() {
        response = endpoint.target(crud.DELETE, "user/" + user.getId(), null);
        assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
    }

    private void get(crud crud) {
        switch (crud) {
            case CREATE:
                checkBean(endpoint.target("user/" + user.getId(), BeanUser.class), true);
                break;
            case UPDATE:
                checkBean(endpoint.target("user/" + user.getId(), BeanUser.class), false);
                break;
            case DELETE:
                endpoint.target("user/" + user.getId(), BeanUser.class);
                break;
            default:
                break;
        }
    }

    private void list() {
        List<BeanUser> list = endpoint.target("user/list", List.class);

        assertNotNull(list);
        assertEquals(0, list.size());
    }

    private void checkBean(BeanUser user, boolean isCreate) {
        System.out.println(result);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAudit().getCreateTime());

        if (isCreate) {
            assertEquals(0L, result.getAudit().getModifyTime());
        } else {
            assertNotEquals(0L, result.getAudit().getModifyTime());
        }

        assertEquals(user.getUsername(), result.getUsername());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getAudit().getUserId(), result.getAudit().getUserId());
    }

    private static final BeanUser user = new BeanUser();

    public BeanUser getEntityToCreate() {
        user.setUsername("user");
        user.setLastName("last");
        user.getAudit().setUserId("1");

        return user;
    }

    public BeanUser getEntityToUpdate() {
        user.setUsername("user" + "upd");
        user.setLastName("last" + "upd");

        return user;
    }

}
