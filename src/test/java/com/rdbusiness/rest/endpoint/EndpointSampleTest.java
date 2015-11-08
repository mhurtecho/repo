package com.rdbusiness.rest.endpoint;

import com.rdbusiness.rest.bean.BeanSample;
import com.rdbusiness.rest.endpoint.EndpointTest.crud;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.*;

public class EndpointSampleTest {

    private Entity<BeanSample> entity;
    private Response response;
    private EndpointTest endpoint;
    private BeanSample result;


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
        response = endpoint.target(crud.CREATE, "sample/add", entity);

        assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());

        result = response.readEntity(BeanSample.class);

        checkBean(sample, true);

        sample.setId(result.getId());
        sample.getAudit().setCreateTime(result.getAudit().getCreateTime());
    }

    private void update() {
        entity = Entity.entity(getEntityToUpdate(), MediaType.APPLICATION_JSON);
        response = endpoint.target(crud.UPDATE, "sample/" + sample.getId(), entity);

        assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());

        result = response.readEntity(BeanSample.class);

        checkBean(sample, false);

        sample.getAudit().setModifyTime(result.getAudit().getModifyTime());
    }

    private void delete() {
        response = endpoint.target(crud.DELETE, "sample/" + sample.getId(), null);
        assertEquals(response.getStatusInfo().getReasonPhrase(), 200, response.getStatus());
    }

    private void get(crud crud) {
        switch (crud) {
            case CREATE:
                checkBean(endpoint.target("sample/" + sample.getId(), BeanSample.class), true);
                break;
            case UPDATE:
                checkBean(endpoint.target("sample/" + sample.getId(), BeanSample.class), false);
                break;
            case DELETE:
                endpoint.target("sample/" + sample.getId(), BeanSample.class);
                break;
            default:
                break;
        }
    }

    private void list() {
        List<BeanSample> list = endpoint.target("sample/list", List.class);

        assertNotNull(list);
        assertEquals(0, list.size());
    }

    private void checkBean(BeanSample sample, boolean isCreate) {
        System.out.println(result);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getAudit().getCreateTime());

        if (isCreate) {
            assertEquals(0L, result.getAudit().getModifyTime());
        } else {
            assertNotEquals(0L, result.getAudit().getModifyTime());
        }

        assertEquals(sample.getField1(), result.getField1());
        assertEquals(sample.getField2(), result.getField2());
        assertEquals(sample.getAudit().getUserId(), result.getAudit().getUserId());
    }

    private static final BeanSample sample = new BeanSample();

    public BeanSample getEntityToCreate() {
        sample.setField1("sample");
        sample.setField2(0);
        sample.getAudit().setUserId("1");

        return sample;
    }

    public BeanSample getEntityToUpdate() {
        sample.setField1("sample" + "upd");
        sample.setField2(1);

        return sample;
    }

}
