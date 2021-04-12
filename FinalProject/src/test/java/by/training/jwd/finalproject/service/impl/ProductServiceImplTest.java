package by.training.jwd.finalproject.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.training.jwd.finalproject.dao.ProductDAO;
import by.training.jwd.finalproject.dao.Transaction;
import by.training.jwd.finalproject.dao.exception.DAOException;
import by.training.jwd.finalproject.dao.exception.TransactionException;
import by.training.jwd.finalproject.dao.impl.ProductDAOImpl;
import by.training.jwd.finalproject.entity.Product;
import by.training.jwd.finalproject.service.ProductService;
import by.training.jwd.finalproject.service.ServiceException;

import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
	private ProductDAO productDAO;
	private Transaction transaction;
	private ProductService productService;

	@Before
	public void setUp() {
		productDAO = mock(ProductDAOImpl.class);
		transaction = mock(Transaction.class);
		productService = new ProductServiceImpl();
	}

	@After
	public void tearDown() {
		productDAO = null;
		transaction = null;
		productService = null;
	}

	@Test
	public void addPositiveTest() throws DAOException, ServiceException, TransactionException {

		when(transaction.addProductAndImage(any(Product.class))).thenReturn(true);
		boolean actual = productService.addProduct("title", "1.1", "facture", "applicationArea", "imageName");
		Assert.assertTrue(actual);

	}

	@Test
	public void addNegativeTest() throws DAOException, ServiceException, TransactionException {

		when(transaction.addProductAndImage(any(Product.class))).thenReturn(true);
		boolean actual = productService.addProduct(" ", null, null, null, "imageName");
		Assert.assertFalse(actual);

	}

	@Test
	public void updatePositiveTest() throws DAOException, ServiceException {

		when(productDAO.findById(any(Integer.class))).thenReturn(Optional.of(new Product()));
		when(productDAO.update(any(Product.class))).thenReturn(true);
		boolean actual = productService.updateProduct("3", "title", "1.1", "facture", "applicationArea");
		Assert.assertTrue(actual);

	}

	@Test
	public void updateNegativeTest() throws DAOException, ServiceException {

		when(productDAO.findById(any(Integer.class))).thenReturn(Optional.of(new Product()));
		when(productDAO.update(any(Product.class))).thenReturn(true);
		boolean actual = productService.updateProduct(" ", null, null, null, "applicationArea");
		Assert.assertFalse(actual);

	}

}