package org.lanqiao.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Order;
import org.lanqiao.entity.Product;
import org.lanqiao.entity.ProductType;
import org.lanqiao.entity.User;
import org.lanqiao.impl.OrderDaoImpl;
import org.lanqiao.impl.ProductDaoImpl;
import org.lanqiao.impl.ProductTypeDaoImpl;
import org.lanqiao.impl.UserDaoImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Management
 */
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManagementServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
		case "商品种类查询":
			List<ProductType> list = new ProductTypeDaoImpl().selectAllProductType();
			JSONArray array = JSONArray.fromObject(list);
			response.getWriter().print(array);
			break;
		case "商品种类插入":
			if (!"".equals(request.getParameter("productTypeId")) && !"".equals(request.getParameter("productType"))) {
				int productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
				String productType = request.getParameter("productType");
				ProductTypeDaoImpl productTypeDao = new ProductTypeDaoImpl();
				if (productTypeDao.selectProductType(
						"select * from productypeinfo where productTypeId=? or productType=?",
						new Object[] { productTypeId, productType })) {
					if (productTypeDao.insertProductType("insert into productypeinfo values(?,?)",
							new Object[] { productTypeId, productType })) {
						response.getWriter().print("true");
					} else {
						response.getWriter().print("false");
					}
				} else {
					response.getWriter().print("false");
				}
			} else {
				response.getWriter().print("false");
			}
			break;
		case "商品种类删除":
			int productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
			new ProductTypeDaoImpl().deleteProductType("delete from productypeinfo where productTypeId=?",
					new Object[] { productTypeId });
			break;
		case "商品查询":
			ProductDaoImpl productDaoImpl1 = new ProductDaoImpl();
			int count = productDaoImpl1.selectCount("select count(*) from productinfo");
			JSONObject obj = new JSONObject();
			List<Product> list1 = null;

			int currPage = Integer.parseInt(request.getParameter("currPage"));
			list1 = productDaoImpl1.selectProductByPage((currPage - 1) * 5);

			obj.put("p", count);
			obj.put("l", list1);
			JSONArray array1 = JSONArray.fromObject(obj);
			response.getWriter().print(array1);
			break;
		case "商品删除":
			int productId = Integer.parseInt(request.getParameter("productId"));
			new ProductTypeDaoImpl().deleteProductType("delete from productinfo where productId=?",
					new Object[] { productId });
			break;
		case "商品插入":
			String productId1 = request.getParameter("productId");
			String productName = request.getParameter("productName");
			String productPrice = request.getParameter("productPrice");
			String productInventory = request.getParameter("productInventory");
			String productDetail = request.getParameter("productDetail");
			String productType = request.getParameter("productType");
			String defaultImg = request.getParameter("defaultImg");
			if (!"".equals(productId1) && !"".equals(productName) && !"".equals(productPrice)
					&& !"".equals(productInventory) && !"".equals(productDetail) && !(productType == null)
					&& !(defaultImg == null)) {
				ProductDaoImpl productDaoImpl = new ProductDaoImpl();
				// 根据商品类型查出商品类型id
				int productTypeId1 = productDaoImpl.selectProductTypeId(
						"select productTypeId from productypeinfo where productType=?", productType);
				// 查询是否存在重复商品
				if (true == productDaoImpl.selectProductIfExist(
						"select * from productinfo where productId=? or productName=?",
						new Object[] { Integer.parseInt(productId1), productName })) {
					boolean b = productDaoImpl.insertProduct("insert into productinfo values(?,?,?,?,?,?,?)",
							new Object[] { Integer.parseInt(productId1), productName, Float.valueOf(productPrice),
									Integer.parseInt(productInventory), productDetail, productTypeId1,
									defaultImg.substring(defaultImg.lastIndexOf("\\") + 1) });
					if (b == true) {
						response.getWriter().print(productTypeId1);
					} else {
						response.getWriter().print(-1);
					}
				} else {
					response.getWriter().print(-1);
				}

			} else {
				response.getWriter().print(0);
			}
			break;
		case "更新商品库存":	
			//String changePrice = request.getParameter("productPrice");
			int changeId=Integer.parseInt(request.getParameter("productId"));
			String changeInventory = request.getParameter("productInventory");
			ProductDaoImpl productDaoImpl = new ProductDaoImpl();
			boolean b=productDaoImpl.changeInventory("update productinfo set productInventory=? where productId=?", new Object[]{changeInventory,changeId});
			if(b){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
			break;
		case "更新商品单价":
			float changePrice = Float.valueOf(request.getParameter("productPrice"));
			int changeId1=Integer.parseInt(request.getParameter("productId"));
			ProductDaoImpl productDaoImpl2 = new ProductDaoImpl();
			boolean b2=productDaoImpl2.changePrice("update productinfo set productPrice=? where productId=?", new Object[]{changePrice,changeId1});
			if(b2){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
			break;
		case "用户订单管理":
			List<Order> orders = new OrderDaoImpl().selectOrders("select * from orderinfo");
			OrderDaoImpl orderDaoImpl=new OrderDaoImpl();
			JSONArray json=new JSONArray();
			//ProductDaoImpl productDaoImpl2=new ProductDaoImpl();
			for (int i = 0; i < orders.size(); i++) {
				JSONObject orderRow=new JSONObject();
				orderRow.put("orderId",orders.get(i).getOrderId());
				orderRow.put("orderDate",orders.get(i).getOrderDate());
				orderRow.put("orderPrice",orders.get(i).getOrderPrice());
				//一次循环为一个订单的商品List
				List<Product> product=orderDaoImpl.selectOrderProducts("select p.productName,p.defaultImg from orderinfo o,orderitem i,productinfo p where o.orderId=i.orderId and i.productId=p.productId and o.orderId=?", orders.get(i).getOrderId());
				orderRow.put("productList", product);
				//一次循环为一个订单的用户信息
				User user=orderDaoImpl.selectOrderUser("select u.userName,u.phoneNumber from orderinfo o,userinfo u where o.userId=u.userId and o.orderId=?", orders.get(i).getOrderId());
				orderRow.put("user", user);
				//一次循环为一个订单的地址信息
				String address=orderDaoImpl.selectOrderAddress("select  a.* from orderinfo o,addressinfo a where o.addressId=a.addressId and o.orderId=?", orders.get(i).getOrderId());
				orderRow.put("address", address);
				String orderStatus=orderDaoImpl.selectOrderStatus("select s.orderStatus from orderInfo o,orderstatusinfo s where o.orderStatusId=s.orderStatusId and o.orderId=? ",orders.get(i).getOrderId());
				orderRow.put("orderStatus", orderStatus);
				json.add(orderRow);
			}
			response.getWriter().print(json);
			break;
		case "用户管理":
			UserDaoImpl userDaoImpl = new UserDaoImpl();
			List<User> userList = userDaoImpl.selectAllUser();
			JSONArray array2=JSONArray.fromObject(userList);
			response.getWriter().print(array2);
			//int count1 = userDaoImpl.selectCount("select count(*) from userinfo");
			//Map<String, Object> map = new HashMap<String, Object>();
			//map.put("total", count1);
			//map.put("rows", userList);
			//response.getWriter().print(JSONObject.fromObject(map).toString());
			break;
		case "用户锁定":
			String userStatus = request.getParameter("userStatus");
			int userId = Integer.parseInt(request.getParameter("userId"));
			boolean b1;
			if("已锁定".equals(userStatus)){
				b1=new UserDaoImpl().lockUser("update userinfo set userStatus=? where userId=?",new Object[]{"未锁定",userId});
			}else{
				b1=new UserDaoImpl().lockUser("update userinfo set userStatus=? where userId=?",new Object[]{"已锁定",userId});
			}
			response.getWriter().println(b1);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
