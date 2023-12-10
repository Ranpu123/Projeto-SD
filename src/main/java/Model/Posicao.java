/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import Model.Vec2;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * @author vinic
 */
public class Posicao {
    @NotNull
    private Double x;
    @NotNull
    private Double y;
    //@NotNull
    private Double z;

    public Posicao(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = 0.0;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
    
    public Vec2 toVec(){
        Vec2 out = new Vec2(this.x, this.y);
        return out;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }    
}
