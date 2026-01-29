package com.wariumapi.vehicle;

/**
 * Thin wrapper over existing WariumVS vehicle control logic.
 */
public interface VehicleController {
    ControlState getControlState();

    void setControlState(ControlState state);
}