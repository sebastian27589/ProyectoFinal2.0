package logico;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelSimulacionAnim extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float opacity = 1.0f;
    
    public PanelSimulacionAnim() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.dispose();
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }

    public float getOpacity() {
        return opacity;
    }

    public void Desaparecer(int delay) {
        Timer timer = new Timer(delay, new ActionListener() {
            private float currentOpacity = opacity;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentOpacity -= 0.05f;
                if (currentOpacity <= 0) {
                    setVisible(false);
                    ((Timer) e.getSource()).stop();
                } else {
                    setOpacity(currentOpacity);
                }
            }
        });
        timer.start();
    }

    public void Aparecer(int delay) {
        setVisible(true);
        Timer timer = new Timer(delay, new ActionListener() {
            private float currentOpacity = 0.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentOpacity += 0.05f;
                if (currentOpacity >= 1.0f) {
                    setOpacity(1.0f);
                    ((Timer) e.getSource()).stop();
                } else {
                    setOpacity(currentOpacity);
                }
            }
        });
        timer.start();
    }
}
