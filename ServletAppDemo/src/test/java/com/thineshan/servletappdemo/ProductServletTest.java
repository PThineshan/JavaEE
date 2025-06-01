package com.thineshan.servletappdemo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServletTest {

    private ProductServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private StringWriter stringWriter;
    private PrintWriter printWriter;

    @BeforeEach
    public void setUp() throws Exception {
        servlet = new ProductServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void testDoGet_withProductParams_shouldDisplayProductTable() throws Exception {
        // Arrange
        when(request.getParameter("pid")).thenReturn("101");
        when(request.getParameter("pname")).thenReturn("Laptop");
        when(request.getParameter("price")).thenReturn("1500");

        // Act
        servlet.doGet(request, response);
        printWriter.flush();

        // Assert
        String output = stringWriter.toString();
        assertTrue(output.contains("Product ID</th><td>101"));
        assertTrue(output.contains("Product Name</th><td>Laptop"));
        assertTrue(output.contains("Product Price</th><td>1500"));
    }

    @Test
    public void testDoPost_shouldDelegateToDoGet() throws Exception {
        // Arrange
        when(request.getParameter("pid")).thenReturn("202");
        when(request.getParameter("pname")).thenReturn("Phone");
        when(request.getParameter("price")).thenReturn("999");

        // Act
        servlet.doPost(request, response);
        printWriter.flush();

        // Assert
        String output = stringWriter.toString();
        assertTrue(output.contains("Product ID</th><td>202"));
        assertTrue(output.contains("Product Name</th><td>Phone"));
        assertTrue(output.contains("Product Price</th><td>999"));
    }
}