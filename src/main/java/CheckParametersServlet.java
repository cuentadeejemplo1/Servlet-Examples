import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author Xavi Torrens
 */
public class CheckParametersServlet extends HttpServlet {


    // Me
                "<th>Param Name</th><th>Param Value(s)</th>\n"+
                "</tr>\n");


        Enumeration paramNames = request.getParameterNames();

        if (!paramNames.hasMoreElements()){
            // Set error code and reason.
            response.sendError(400, "Need parameters!!!" );
        } else {

            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                out.print("<tr><td>" + paramName + "</td>\n<td>");
                String[] paramValues =
                        request.getParameterValues(paramName);
                // Read single valued data
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() == 0)
                        out.println("<i>No Value</i>");
                    else
                        out.println(paramValue);
                } else {
                    // Read multiple valued data
                    out.println("<ul>");
                    for (int i = 0; i < paramValues.length; i++) {
                        out.println("<li>" + paramValues[i]);
                    }
                    out.println("</ul>");
                }
            }
            out.println("</tr>\n</table>\n</body></html>");
        }
    }
}
