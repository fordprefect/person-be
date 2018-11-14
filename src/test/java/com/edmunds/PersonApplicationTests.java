package com.edmunds;

import com.edmunds.model.Person;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonApplicationTests extends AbstractTest{

    @Test
    public void contextLoads() {
    }

    @Test
    public void getPersonById() throws Exception {
        String uri = "/people/1";
        validatePersonExists(uri);
    }

    @Test
    public void getPersonByEmail() throws Exception {
        // Base64 encoded email (rswanson@edmunds.com)
        String uri = "/people/email/cnN3YW5zb25AZWRtdW5kcy5jb20=";
        validatePersonExists(uri);
    }

    private void validatePersonExists(String uri) throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Person person = super.mapFromJson(content, Person.class);
        assertNotNull(person);
    }


}

