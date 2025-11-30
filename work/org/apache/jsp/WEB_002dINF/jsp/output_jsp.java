package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.uity.model.Regression;
import com.uity.model.RegressionAnalysis;

public final class output_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fspring_005furl_005fvar_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fspring_005furl_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fspring_005furl_005fvar_005fvalue_005fnobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\" />\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("\r\n");
      out.write("<title>Hello Spring Web MVC</title>\r\n");
      if (_jspx_meth_spring_005furl_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${bootstrapCss}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" rel=\"stylesheet\" />\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
  String living = request.getParameter("living");
	String lot = request.getParameter("lot");
	String above = request.getParameter("above");
	String base = request.getParameter("base");
	String bed =  request.getParameter("bed");
	String bath =  request.getParameter("bath");
	String floor =  request.getParameter("floor");
	String year =  request.getParameter("year");
	String zip =  request.getParameter("zip");
	
	Double price;
	
	Regression r = new Regression();
	Double[] d=r.Reg();
	price= d[9]*-1+d[0]*-1*Double.parseDouble(bed)+d[1]*Double.parseDouble(bath)+d[2]*Double.parseDouble(floor)+d[3]*-1*Double.parseDouble(year)+d[4]*Double.parseDouble(zip)+d[5]*Double.parseDouble(living)+d[6]*-1*Double.parseDouble(lot)+d[7]*Double.parseDouble(above)+d[8]*Double.parseDouble(base);

      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<div class=\"container\">\r\n");
      out.write("\t\t<div class=\"text-center\">\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<h3>Housing Guide: Predicting Housing Prices</h3>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<p>You want a house with following specifications.</p>\r\n");
      out.write("\t\t\t<table class=\"table table-bordered table-hover\">\r\n");
      out.write("\t\t\t\t<thead style=\"background: #3179c6; font-size:18px ; color: white;\">\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th scope=\"col\">House Specifications</th>\r\n");
      out.write("\t\t\t\t\t\t<th scope=\"col\">Measurements and Values</th>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</thead>\r\n");
      out.write("\t\t\t\t<tbody>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Number of bedrooms</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(bed );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Number of bathrooms</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(bath );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Number of floors</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(floor );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Year Built</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(year );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Zipcode</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(zip );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Living Room Square Feet</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(living  );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Lot Square Feet</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(lot  );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Above Square feet</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(above  );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<th>Basement Square Feet</th>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(base  );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</tbody>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t<br>\r\n");
      out.write("\t\t\t<p>Price of the house shall be  </p> ( <p style=\"font-style: italic; font-family: 'Times New Roman', Times, serif; font-size: 18px; font-weight: bold;\">");
      out.print(price  );
      out.write("</p> ) \r\n");
      out.write("\t\t\t<p>   in USD.</p>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t<!--  \r\n");
      out.write("\t\t\t<p> d[9] is ");
      out.print(d[9]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[0] is ");
      out.print(d[0]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[1] is ");
      out.print(d[1]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[2] is ");
      out.print(d[2]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[3] is ");
      out.print(d[3]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[4] is ");
      out.print(d[4]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[5] is ");
      out.print(d[5]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[6] is ");
      out.print(d[6]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[7] is ");
      out.print(d[7]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t<p> d[8] is ");
      out.print(d[8]);
      out.write("</p><br/>\r\n");
      out.write("\t\t\t-->\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<br>\r\n");
      out.write("\r\n");
      out.write("\t<footer>\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<form method=\"POST\" action=\"evaluate.do\" id=\"myForm\">\r\n");
      out.write("\t\t<p align=\"center\"> \r\n");
      out.write("\t\t<a  style=\"color: blue; text-decoration: underline; cursor:pointer;\" onclick=\"document.getElementById('myForm').submit()\">Check accuracy for the existing dataset.</a> <br>\r\n");
      out.write("\t\tcopyright © Aditya Verma 2025</p>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<br>\r\n");
      out.write("\t</footer>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<!--  \r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function rsq()\r\n");
      out.write("{\r\n");
      out.write("\t");
 
	RegressionAnalysis ra = new RegressionAnalysis();
	double result = ra.RA();
	
      out.write("\r\n");
      out.write("\talert (\"Coefficient of Determination R squared for the dataset of house price prediction is    \" + ");
      out.print(result );
      out.write(" + \" .   It is better when R squared approaches to 1.\");\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("-->\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /WEB-INF/jsp/output.jsp(14,7) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("./css/bootstrap.css");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_spring_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  spring:url
    org.springframework.web.servlet.tags.UrlTag _jspx_th_spring_005furl_005f0 = (org.springframework.web.servlet.tags.UrlTag) _005fjspx_005ftagPool_005fspring_005furl_005fvar_005fvalue_005fnobody.get(org.springframework.web.servlet.tags.UrlTag.class);
    _jspx_th_spring_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_spring_005furl_005f0.setParent(null);
    // /WEB-INF/jsp/output.jsp(18,0) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005furl_005f0.setValue("/resources/core/css/bootstrap.css");
    // /WEB-INF/jsp/output.jsp(18,0) name = var type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_spring_005furl_005f0.setVar("bootstrapCss");
    int[] _jspx_push_body_count_spring_005furl_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_spring_005furl_005f0 = _jspx_th_spring_005furl_005f0.doStartTag();
      if (_jspx_th_spring_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_spring_005furl_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_spring_005furl_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_spring_005furl_005f0.doFinally();
      _005fjspx_005ftagPool_005fspring_005furl_005fvar_005fvalue_005fnobody.reuse(_jspx_th_spring_005furl_005f0);
    }
    return false;
  }
}
