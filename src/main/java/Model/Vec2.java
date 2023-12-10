/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vinic
 */
public record Vec2(double x, double y){

    public Vec2 normalize(){
        Vec2 vec = new Vec2(this.x()/getLength(), this.y()/getLength());
        return vec;
    }
    
    public double getLength(){
        return Math.sqrt(Math.pow(this.x(), 2)+Math.pow(this.y(), 2));
    }
    
    public static double dot(Vec2 v1, Vec2 v2){
        return (v1.x() * v2.x()) + (v1.y() * v2.y());
    }
    
    public static double det(Vec2 v1, Vec2 v2){
        return (v1.x() * v2.y()) - (v1.y() * v2.x());
    }
    
    public double angle(Vec2 vec){
        Vec2 v1 = new Vec2(this.x(), this.y());
        Vec2 v2 = new Vec2(vec.x, vec.y);
        
        v1 = v1.normalize();
        v2 = v2.normalize();
        
        double dot = dot(v1,v2);
        
        return det(v1,v2) < 0 ? -Math.acos(dot) : Math.acos(dot);
    }
    
    public static Vec2 directionTo(Vec2 orig, Vec2 dst){
        Vec2 out = new Vec2((dst.x() - orig.x()), (dst.y() - orig.y()));
        return out;
    }
    
}
