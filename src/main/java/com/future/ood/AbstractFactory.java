package com.future.ood;

/**
 * The purpose of the Abstract Factory is to provide an interface for creating families of related objects,
 * without specifying concrete classes.
 *
 * Scenario:
 *
 * You go to a car dealership, and said, I need a Toyota, then you will get a Toyota after you paid money.
 *
 * The next day, you won powerball, so you come to another dealership, and asked a Lamborghini...
 *
 * As a client, you got a car, but you don't have to know how people made the cars.
 *
 * Created by xingfeiy on 4/4/18.
 */
public class AbstractFactory {

    private enum CarBrand{
        TOYOTA,

        LAMBORGHINI
    }

    private abstract class CarDealership{

        public CarDealership getDealerShip(CarBrand brand) {
            switch (brand){
                case TOYOTA:
                    return new ToyotaDealership();
                case LAMBORGHINI:
                    return new LamborghiniDealership();
                default:
                    return null;
            }
        }

        public abstract void getCar();

        public abstract void getPackage();
    }

    private class ToyotaDealership extends CarDealership {
        @Override
        public void getCar() {

        }

        @Override
        public void getPackage() {

        }
    }

    private class LamborghiniDealership extends CarDealership{
        @Override
        public void getCar() {

        }

        @Override
        public void getPackage() {

        }
    }




}
