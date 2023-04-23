package api.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.opencsv.CSVReader;

public class CSV_DataProvider {

	
@DataProvider(name ="UsersData")
    public static Iterator<Object[]> getUsersData() throws Exception {
        String filePath =System.getProperty("user.dir")+"\\testdata\\UserData.csv"; // path of your csv file
        try (CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(filePath)))) {
            List<String[]> records = csvReader.readAll();
            int numRecords = records.size();
            if (numRecords == 0) {
                throw new Exception("CSV file is empty");
            }
            List<Object[]> dataList = new ArrayList<Object[]>();
            Iterator<String[]> iterator = records.iterator();
            while (iterator.hasNext()) {
                String[] record = iterator.next();
                Object[] data = new Object[record.length];
                for (int i = 0; i < record.length; i++) {
                    data[i] = record[i];
                }
                dataList.add(data);
            }
            return dataList.iterator();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    
    @DataProvider(name ="UserNames")
    public static Iterator<Object[]> getUserNameData() throws Exception {
        String filePath =System.getProperty("user.dir")+"\\testdata\\UserNames.csv"; // path of your csv file
        try (CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(filePath)))) {
            List<String[]> records = csvReader.readAll();
            int numRecords = records.size();
            if (numRecords == 0) {
                throw new Exception("CSV file is empty");
            }
            List<Object[]> dataList = new ArrayList<Object[]>();
            Iterator<String[]> iterator = records.iterator();
            while (iterator.hasNext()) {
                String[] record = iterator.next();
                Object[] data = new Object[record.length];
                for (int i = 0; i < record.length; i++) {
                    data[i] = record[i];
                }
                dataList.add(data);
            }
            return dataList.iterator();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}