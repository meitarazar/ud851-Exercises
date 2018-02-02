package com.example.android.boardingpass;

/*
* Copyright (C) 2016 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.boardingpass.databinding.ActivityMainBinding;
import com.example.android.boardingpass.utilities.FakeDataUtils;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //COMPLETED (3) Create a data binding instance called mBinding of type ActivityMainBinding
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // COMPLETED (4) Set the Content View using DataBindingUtil to the activity_main layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // COMPLETED (5) Load a BoardingPassInfo object with fake data using FakeDataUtils
        BoardingPassInfo info = FakeDataUtils.generateFakeBoardingPassInfo();

        // COMPLETED (9) Call displayBoardingPassInfo and pass the fake BoardingInfo instance
        displayBoardingPassInfo(info);
    }

    private void displayBoardingPassInfo(BoardingPassInfo info) {

        // COMPLETED (6) Use mBinding to set the Text in all the textViews using the data in info
        binding.textViewPassengerName.setText(info.passengerName);
        binding.textViewFlightCode.setText(info.flightCode);
        binding.textViewOriginAirport.setText(info.originCode);
        binding.textViewDestinationAirport.setText(info.destCode);
        binding.textViewTerminal.setText(info.departureTerminal);
        binding.textViewGate.setText(info.departureGate);
        binding.textViewSeat.setText(info.seatNumber);

        // COMPLETED (7) Use a SimpleDateFormat formatter to set the formatted value in time text views
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String boarding = sdf.format(info.boardingTime);
        String departure = sdf.format(info.departureTime);
        String arrival = sdf.format(info.arrivalTime);

        binding.textViewBoardingTime.setText(boarding);
        binding.textViewDepartureTime.setText(departure);
        binding.textViewArrivalTime.setText(arrival);

        // COMPLETED (8) Use TimeUnit methods to format the total minutes until boarding
        long countdown = info.getMinutesUntilBoarding();
        long hours = TimeUnit.MINUTES.toHours(countdown);
        long minutes = countdown - TimeUnit.HOURS.toMinutes(hours);

        String sCountdown = getString(R.string.countDownFormat, hours, minutes);

        binding.textViewBoardingInCountdown.setText(sCountdown);
    }
}

