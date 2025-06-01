package com.thineshan.servletdemo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloServletTest {
    private HelloServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new HelloServlet();
        servlet.init(); // Call the init method to initialize message
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    public void testDoGet() throws Exception {
        servlet.doGet(request, response);
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello World!"), "Response should contain 'Hello World!'");
    }
}
