package solr;

import java.io.File;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;

public class SolrDemo {

	public static String DEFAULT_URL = "http://localhost:8983/solr/file";

	public static final String FILE_NAME = "e:\\word.docx";

	public static void main(String[] args) {
		String solrId = "魅貂蝉.docx";   
    try  
    {  
        indexFilesSolrCell(FILE_NAME, solrId);  
    }  
    catch (IOException e)  
    {  
        e.printStackTrace();  
    }  
    catch (SolrServerException e)  
    {  
        e.printStackTrace();  
    }  
		try {
			
			
			
			//开启服务端
//			SolrClient server = new HttpSolrClient(DEFAULT_URL);
			//上传文件
//			ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract"); 
//			String contentType="application/pdf";  
//      up.addFile(new File(FILE_NAME), contentType);  
//      up.setParam("literal.id", "solr用户指南中文版.pdf");  
//      up.setParam("uprefix", "attr_");  
//      up.setParam("fmap.content", "attr_content");  
//      up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);  
//      server.request(up);
			
//      QueryResponse rsp = server.query(new SolrQuery("用户"));  
//      System.out.println(rsp);  
      
//      server.add(doc);
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("id", "A");
//			SolrParams params = new MapSolrParams(map);
//			QueryResponse query = server.query(params);
//			Map<String, Object> debugMap = query.getDebugMap();
//			Iterator<Entry<String, Object>> iterator = debugMap.entrySet().iterator();
//			while(iterator.hasNext()) {
//				Entry<String, Object> temp = iterator.next();
//				System.out.println(temp.getKey() + ":" + temp.getValue());
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/** 从文件创建索引 
   * <功能详细描述> 
   * @param fileName 
   * @param solrId 
   * @see [类、类#方法、类#成员] 
   */  
  public static void indexFilesSolrCell(String fileName, String solrId)   
      throws IOException, SolrServerException  
  {  
			//开启服务端
			SolrClient solr = new HttpSolrClient(DEFAULT_URL); 
      ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");  
        
      String contentType="application/word";  
      up.addFile(new File(fileName), contentType);  
      up.setParam("literal.id", solrId);  
      up.setParam("uprefix", "attr_");  
      up.setParam("fmap.content", "attr_content");  
      up.setParam("path", fileName);
      up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);  
      solr.request(up);  
      QueryResponse rsp = solr.query(new SolrQuery("*:*"));  
      System.out.println(rsp);  
      solr.close();
  }  
    
}
