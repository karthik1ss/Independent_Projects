
package app;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import JdbcConn.ConnectionUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;


public class FileUploadAction extends ActionSupport
{
    private File[] file;
    private String[] filename;
    private String[] contenttype;
    private String title;
    private String category;
    private String description;
    
    public FileUploadAction() 
    {}

    public String[] getContenttype() {
        return contenttype;
    }

    public void setContenttype(String[] contenttype) {
        this.contenttype = contenttype;
    }

    public File[] getFile() {
        return file;
    }

    public void setFile(File[] file) {
        this.file = file;
    }

    public String[] getFilename() {
        return filename;
    }

    public void setFilename(String[] filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String execute() throws Exception 
    {
        System.out.println("video"+title+"saved");
        ServletContext servletContext = ServletActionContext.getServletContext();
        String dataDir = servletContext.getRealPath("/Videos");
        Connection conn;
        Blob blob;
        conn=ConnectionUtil.getConnection();
        String insertcmd="INSERT INTO UserSpecific VALUES(?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(insertcmd);
        for (int i = 0; i < file.length; i++)
        {
                file[i]=new File(dataDir, filename[i]);
                int filelength=(int)file[i].length();
                byte[] data=new byte[filelength];
                InputStream source=new FileInputStream(file[i]);
                source.read(data,0,filelength);
                source.close();
                blob=conn.createBlob();
                blob.setBytes(1, data);
                ps.setBlob(i,blob);

        }
        ps.setString(3,category);
        ps.setString(4,description);
        ps.close();
        ConnectionUtil.close(conn);
        return SUCCESS;

     }

    public String doInput() throws Exception
    {
        return INPUT;
    }



   

    

}