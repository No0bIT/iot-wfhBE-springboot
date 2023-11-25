package com.example.IOTBE.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.IOTBE.model.Customer;
import com.example.IOTBE.model.Daily;
import com.example.IOTBE.repository.CustomerRepository;
import com.example.IOTBE.repository.WeatherRepository;
import com.example.IOTBE.trainingModel.Weather;

import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;

@Service
public class PredictionService {

	@Autowired
	WeatherRepository weatherRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	public void createDataTrainCus(int idUser) {
		ArrayList<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("dayOTW"));
        attributes.add(new Attribute("weather"));
        attributes.add(new Attribute("temperature"));
        attributes.add(new Attribute("humidity"));
        attributes.add(new Attribute("totalStep"));
        Instances data = new Instances("NewDaily", attributes, 0);
        
        ArffSaver arffSaver = new ArffSaver();
        arffSaver.setInstances(data);
        try {
            arffSaver.setFile(new File("dataTrainingCustomer/DataTrainingCustomer"+Integer.toString(idUser)+".arff"));
            arffSaver.writeBatch();
            System.out.println("Đã tạo file DataTrainingCustomer" +Integer.toString(idUser)+ ".arff");
        } catch (IOException e) {
            e.printStackTrace();
        }   
	}
	
	
	public void addDataTrainCus(int idUser,Daily daily) throws Exception {
//		DataSource source = new DataSource("dataTrainingCustomer/DataTrainingCustomer"+Integer.toString(idUser)+".arff");
//        Instances data = source.getDataSet();
//		double[] instanceValues = new double[data.numAttributes()];
//		
//		instanceValues[0] =daily.getDayOTW(); //dayOTW
		///weather
		int check=0;
		int weatherTrain=0;
        List<Weather> weathers = weatherRepository.findAll();
        for(Weather weather : weathers) {
        	if(weather.getName().equals(daily.getWeather())){
        		weatherTrain =weather.getId(); // weather
        		check=1;
        		break;
        	}
        }
        if(check==0) {
        	Weather weather = new Weather();
        	weather.setName(daily.getWeather());
        	weatherRepository.save(weather);
        	weatherTrain = weather.getId(); //weather
        }
        ///weather
//		instanceValues[2] =daily.getTemperature();//temperature
//		instanceValues[3] =daily.getHumidity();//humidity
//		instanceValues[4] =daily.getTotalStep();//totalStep
		String arffData = daily.getDayOTW()+","+weatherTrain+","+ daily.getTemperature()+","+daily.getHumidity()+","+daily.getTotalStep();
		
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("dataTrainingCustomer/DataTrainingCustomer"+Integer.toString(idUser)+".arff", true))) {
		    writer.append(arffData + "\n"); // Thêm ký tự xuống dòng để tạo dòng mới
		    System.out.println("Đã ghi dữ liệu vào file ARFF.");
		} catch (IOException e) {
		    System.out.println("Lỗi khi ghi dữ liệu vào file ARFF: " + e.getMessage());
		    e.printStackTrace(); // In stack trace để xác định lỗi cụ thể
		}    
	}

	public int getPrediction(int idUser,Daily daily) throws Exception {
		Customer customer=customerRepository.getById(idUser);
		if(ChronoUnit.DAYS.between(LocalDate.now(), customer.getDate())>=15){
		 	DataSource source = new DataSource("dataTrainingCustomer/DataTrainingCustomer"+Integer.toString(idUser)+".arff");	 	
	        Instances dataset = source.getDataSet();
	        
	        // Thiết lập thuộc tính dự đoán (totalStep) là thuộc tính cuối cùng trong dataset
	        dataset.setClassIndex(dataset.numAttributes() - 1);
	
	        // Tạo mô hình cây quyết định
	        RandomForest randomForest = new RandomForest();
	        randomForest.setNumFeatures(100); // Số lượng cây quyết định
	        // Huấn luyện mô hình
	        randomForest.buildClassifier(dataset);
	        
	        
	        Instance newDaily = new DenseInstance(5);
	        
	        newDaily.setValue(0, daily.getDayOTW());//dayOTW
	        
	        
	        int check=0;

	        List<Weather> weathers = weatherRepository.findAll();
	        for(Weather weather : weathers) {
	        	if(weather.getName().equals(daily.getWeather())){
	        		newDaily.setValue(1,weather.getId()); // weather
	        		check=1;
	        		break;
	        	}
	        }
	        if(check==0) {
	        	Weather weather = new Weather();
	        	weather.setName(daily.getWeather());
	        	weatherRepository.save(weather);
	        	newDaily.setValue(1,weather.getId()); //weather
	        }
	        newDaily.setValue(2, daily.getTemperature());
	        newDaily.setValue(3, daily.getHumidity());
	        newDaily.setValue(4, customer.getSex().equals("Nam")?0:1);
	        newDaily.setValue(5, customer.getHeight());
	        newDaily.setValue(6, customer.getWeight());
	        
	        
	        
	        newDaily.setMissing(7);
	        
	        newDaily.setDataset(dataset);
	        System.out.println("Dự đoán sử dụng datatraining của customer "+idUser);
	        try {
			    int predicted= (int) randomForest.classifyInstance(newDaily); // Dự đoán giá RandomForest			    
			    return predicted;
			} catch (Exception e) {
			    e.printStackTrace();
			}
	        return 0;   
		}
		else
		{
			DataSource source = new DataSource("dataTrainingCustomer/DataTrainingAllCustomer.arff");	 	
	        Instances dataset = source.getDataSet();
	        
	        // Thiết lập thuộc tính dự đoán (totalStep) là thuộc tính cuối cùng trong dataset
	        dataset.setClassIndex(dataset.numAttributes() - 1);
	
	        // Tạo mô hình cây quyết định
	        RandomForest randomForest = new RandomForest();
	        randomForest.setNumFeatures(100); // Số lượng cây quyết định
	        // Huấn luyện mô hình
	        randomForest.buildClassifier(dataset);
	        
	        
	        Instance newDaily = new DenseInstance(8);
	        
	        newDaily.setValue(0, daily.getDayOTW());//dayOTW
	        
	        
	        int check=0;

	        List<Weather> weathers = weatherRepository.findAll();
	        for(Weather weather : weathers) {
	        	if(weather.getName().equals(daily.getWeather())){
	        		newDaily.setValue(1,weather.getId()); // weather
	        		check=1;
	        		break;
	        	}
	        }
	        if(check==0) {
	        	Weather weather = new Weather();
	        	weather.setName(daily.getWeather());
	        	weatherRepository.save(weather);
	        	newDaily.setValue(1,weather.getId()); //weather
	        }
	        newDaily.setValue(2, daily.getTemperature());
	        newDaily.setValue(3, daily.getHumidity());
	        newDaily.setMissing(4);
	        
	        newDaily.setDataset(dataset);
	        System.out.println("Dự đoán sử dụng datatraining của all customer ");
	        try {
			    int predicted= (int) randomForest.classifyInstance(newDaily); // Dự đoán giá RandomForest			    
			    return predicted;
			} catch (Exception e) {
			    e.printStackTrace();
			}
	        return 0;   
			
		}
	}
}
