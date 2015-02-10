/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package papadopoulos_client;

import javax.swing.JFrame;

/**
 *
 * @author alepapadop
 */
public class ClientWindow {
        
    private JFrame _frame = null;
    
    public JFrame GetFrame()
    {
        return _frame;
    }
    
    private void SetFrame(JFrame frame)
    {
        _frame = frame;
    }
    
    public ClientWindow()
    {
        if (_frame == null) {
            JFrame frame = new JFrame();
            SetFrame(frame);
        }
    }
    
}
