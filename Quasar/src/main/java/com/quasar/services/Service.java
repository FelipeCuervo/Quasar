package com.quasar.services;

import com.quasar.entities.Position;
import com.quasar.entities.Satellite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;


@org.springframework.stereotype.Service
public class Service {

    final static double[] kenobi = new double[]{-500, -200};
    final static double[] skywalker = new double[]{100, -100};
    final static double[] sato = new double[]{500, 100};

    ArrayList<Satellite> satelliteArrayList = new ArrayList<>();
    public Position getPosition(List<Satellite>satellites){
        double[][] positions = new double[satellites.size()][];
        double[] distances = new double[satellites.size()];

        for (int i=0;i<satellites.size();i++){
            if (satellites.get(i).getName().equals("kenobi")) {
                distances[i] = satellites.get(i).getDistance();
                Position position= null;
                position = new Position(kenobi);
                double[] satellitePosition = position.asArray();
                positions[i]=satellitePosition;
            } else if (satellites.get(i).getName().equals("skywalker")) {
                distances[i] = satellites.get(i).getDistance();
                Position position= null;
                position = new Position(skywalker);
                double[] satellitePosition = position.asArray();
                positions[i]=satellitePosition;
            } else {
                distances[i] = satellites.get(i).getDistance();
                Position position= null;
                position = new Position(sato);
                double[] satellitePosition = position.asArray();
                positions[i]=satellitePosition;
            }
        }

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances),
                new LevenbergMarquardtOptimizer()
        );
        LeastSquaresOptimizer.Optimum optimum = solver.solve();
        double[] result = optimum.getPoint().toArray();
        return new Position(result);
    }

    public String getMessage(List<String[]> messagesList) throws Exception {
        String[] message = new String[messagesList.get(1).length];
        String[] messageTemp;

        for (int i=0;i<messagesList.size();i++){
            messageTemp=messagesList.get(i);
            for (int j=0;j<messageTemp.length;j++){
                if (!messageTemp[j].isBlank() ){
                    message[j]=messageTemp[j]+" ";
                }
            }
        }

        for (int i=0;i<message.length;i++){
            if (message[i]==null) {
                throw new Exception();
            }
        }
        return Arrays.stream(message).collect(Collectors.joining());
    }

    public void saveSatellite(Satellite satellite){


        Satellite satellite1 = new Satellite();
        satellite1.setName(satellite.getName());
        satellite1.setDistance(satellite.getDistance());
        satellite1.setMessage(satellite.getMessage());
        satelliteArrayList.add(satellite1);

    }

    public Satellite getSatellite(String satelliteName){
        Satellite satelliteOutput = new Satellite();
        for (int i =0;i<satelliteArrayList.size();i++){
            Satellite satellite = satelliteArrayList.get(i);
            if (satellite.getName().equals(satelliteName)){
                 satelliteOutput = new Satellite(satellite.getDistance(),satellite.getMessage());

            }

        }


        return satelliteOutput;
    }
}
