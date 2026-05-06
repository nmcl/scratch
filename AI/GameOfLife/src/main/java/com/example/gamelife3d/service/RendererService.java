package com.example.gamelife3d.service;

import com.example.gamelife3d.model.Cell;
import com.example.gamelife3d.model.World3D;
import jakarta.enterprise.context.ApplicationScoped;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

@ApplicationScoped
public class RendererService {

    public byte[] renderWorldAsImage(String worldId, World3D world, int width, int height, int depth) {
        // Create a 2D projection of the 3D world
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        
        // Render cells - simple projection (you can enhance this for better visualization)
        renderCells(g2d, world, width, height);
        
        g2d.dispose();
        
        return imageToByteArray(image);
    }

    private void renderCells(Graphics2D g2d, World3D world, int imageWidth, int imageHeight) {
        List<Cell> aliveCells = world.getAliveCells();
        
        if (aliveCells.isEmpty()) return;
        
        // Calculate scaling factors
        double scaleX = (double) imageWidth / world.getWidth();
        double scaleY = (double) imageHeight / world.getHeight();
        
        // Render each alive cell
        for (Cell cell : aliveCells) {
            int x = (int) (cell.getX() * scaleX);
            int y = (int) (cell.getY() * scaleY);
            
            // Create a visually appealing color based on generation or position
            Color cellColor = getCellColor(cell);
            g2d.setColor(cellColor);
            
            // Draw a circle or square for the cell
            int size = Math.max(1, (int) (Math.min(scaleX, scaleY) * 0.3));
            g2d.fillOval(x - size/2, y - size/2, size, size);
        }
    }

    private Color getCellColor(Cell cell) {
        // Create color based on generation for visual effect
        int generation = cell.getGeneration();
        int r = Math.min(255, 100 + (generation * 3) % 155);
        int g = Math.min(255, 150 + (generation * 2) % 105);
        int b = Math.min(255, 200 + (generation * 4) % 55);
        
        return new Color(r, g, b);
    }

    private byte[] imageToByteArray(BufferedImage image) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error converting image to byte array", e);
        }
    }

    public byte[] renderSlice(World3D world, int sliceIndex, int sliceType) {
        // sliceType: 0 = X, 1 = Y, 2 = Z
        int width = world.getWidth();
        int height = world.getHeight();
        int depth = world.getDepth();
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Set background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);
        
        // Render slice based on slice type
        renderSlice(g2d, world, sliceIndex, sliceType);
        
        g2d.dispose();
        
        return imageToByteArray(image);
    }

    private void renderSlice(Graphics2D g2d, World3D world, int sliceIndex, int sliceType) {
        int width = world.getWidth();
        int height = world.getHeight();
        int depth = world.getDepth();
        
        switch (sliceType) {
            case 0: // X slice
                for (int y = 0; y < height; y++) {
                    for (int z = 0; z < depth; z++) {
                        if (world.isCellAlive(sliceIndex, y, z)) {
                            g2d.setColor(Color.WHITE);
                            g2d.fillRect(y, z, 2, 2);
                        }
                    }
                }
                break;
            case 1: // Y slice
                for (int x = 0; x < width; x++) {
                    for (int z = 0; z < depth; z++) {
                        if (world.isCellAlive(x, sliceIndex, z)) {
                            g2d.setColor(Color.WHITE);
                            g2d.fillRect(x, z, 2, 2);
                        }
                    }
                }
                break;
            case 2: // Z slice
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        if (world.isCellAlive(x, y, sliceIndex)) {
                            g2d.setColor(Color.WHITE);
                            g2d.fillRect(x, y, 2, 2);
                        }
                    }
                }
                break;
        }
    }
}
