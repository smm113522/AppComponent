//package com.kotlin.image;
//
//import com.kotlin.image.io.FilenameUtils;
//import com.kotlin.image.io.IOUtils;
//
//import org.apache.commons.io.FileUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class Demo {
//
//    public static void main(String[] args) throws IOException {
//        //要抓取图片的网址连接
//        String url = "https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gbk&word=%B6%AB%B7%BD%BD%F0%B9%DD%B3%A4&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=111111";
////        String url = "https://unsplash.com/";
//        //根据连接获取一个Connection对象
//        InputStream is = getConnection(url,null).getInputStream();
//        //调用commonsio工具包中IOUtils的方法，返回HTML内容；
//        String html = IOUtils.toString(is, "GBK");
//        //解析HTML内容，获取所有图片链接地址
//        List<String> picPaths = parseHtml(html);
//        //判断是否获取到图片链接
//        if(picPaths.size() > 0) {
//            //创建一个线程池，处理下载任务
//            ExecutorService es = Executors.newFixedThreadPool(picPaths.size() < 50 ? picPaths.size() : 50);
//            //循环处理资源
//            for (final String picPath : picPaths) {
//                //根据具体的资源，创建下载任务，提交到线程池中
//                es.execute(()->{downLoad(pathHandle(picPath), "F:\\pic",url);});
//            }
//            //关闭线程池
//            es.shutdown();
//        }
//    }
//    /**
//     * 获取唯一序列号，做为文件名
//     * @return
//     */
//    private static String getUUID() {
//        UUID uuid = UUID.randomUUID();
//        return uuid.toString().replaceAll("-", "");
//    }
//    /**
//     * 处理获取到的图片链接
//     * @param picPath
//     * @return
//     */
//    private static String pathHandle(String picPath) {
//        if(!picPath.startsWith("http")) {
//            picPath = "http:" + picPath;
//        }
//        //这个处理，是针对天猫的图片链接，用于下载大图;
//        //天猫的商品图片链接示例如下：
//        //http://img.alicdn.com/bao/uploaded/i4/TB19FGse7KWBuNjy1zjefkOypXa_032207.jpg_b.jpg
//        //去掉最后一个_以后的内容，可以下载大图；否则就下载的是小图
//        if(picPath.indexOf("_") != picPath.lastIndexOf("_")) {
//            picPath = picPath.substring(0,picPath.lastIndexOf("_"));
//        }
//        return picPath;
//    }
//    /**
//     * 下载图片
//     * @param picPath
//     * @param dir
//     * @param referer
//     */
//    private static void downLoad(String picPath, String dir,String referer){
//        try {
//            //生成文件名
//            String name = getUUID()+"."+ FilenameUtils.getExtension(picPath);
//            FileUtils.copyURLToFile(getConnectionUrl(picPath,referer), new File(new File(dir),name));
//            System.out.println(picPath + "下载完毕！");
//        } catch (IOException e) {
//            System.err.println(picPath+ "下载失败！");
//        }
//    }
//    /**
//     * 使用正则表达式解析html内容，获取图片链接
//     * @param html
//     * @return
//     */
//    private static List<String> parseHtml(String html) {
//        String regex = "\"[^\"^(^)^}^>^<^{]+\\.(jpg|png|jpeg|gif)";
//        List<String> list = new ArrayList<>();
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(html);
//        while(m.find()) {
//            list.add(m.group().substring(1));
//        }
//        return list;
//    }
//    /**
//     * 根据url地址，获取一个连接对象，同时设置请求头，避免服务器防盗链，以及模拟浏览器请求
//     * @param url
//     * @param referer
//     * @return
//     */
//    private static URL getConnectionUrl(String url, String referer) {
//        try {
//            URLConnection uc = new URL(url).openConnection();
//            //解决防盗链问题
//            uc.setRequestProperty("referer", referer==null?"http://www.baidu.com/":referer);
//            //模拟浏览器
//            uc.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
//            return uc.getURL();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(url);
//            return null;
//        }
//    }
//
//    private static URLConnection getConnection(String url, String referer) {
//        try {
//            URLConnection uc = new URL(url).openConnection();
//            //解决防盗链问题
//            uc.setRequestProperty("referer", referer==null?"http://www.baidu.com/":referer);
//            //模拟浏览器
//            uc.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
//            return uc;
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(url);
//            return null;
//        }
//    }
//
//}
