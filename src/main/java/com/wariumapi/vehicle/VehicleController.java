package com.wariumapi.vehicle;


public interface VehicleController {
    ControlState getControlState();

    void setControlState(ControlState state);
}