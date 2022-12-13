package dynamic_beat_custom;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	@Override 
	public void keyPressed(KeyEvent e) { // key를 눌렀을 때의 이벤트 처리
		System.out.println(e.getKeyCode());
		if(DynamicBeat.game == null) {
			return;
			// 현재 게임이 진행되고 있지 않다면 return을 넣어줘서 아래의 if문들을 수행하지 않도록 함
			//==> 키보드 입력하더라도 어떠한 변화도 일어나지 않도록 키보드 입력을 무력화시킴.
		}
		// 키보드 입력 처리 (게임진행 O일 때)
		if(e.getKeyCode() == KeyEvent.VK_A) {
			DynamicBeat.game.press0();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.press1();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.press2();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.press3();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.press4();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.press5();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SEMICOLON) {
			DynamicBeat.game.press6();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) { // key를 눌렀다가 뗄 때의 이벤트 처리  
		if(DynamicBeat.game == null) {
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			DynamicBeat.game.release0();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			DynamicBeat.game.release1();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.release2();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F || e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE) {
			DynamicBeat.game.release3();
		}
		else if(e.getKeyCode() == KeyEvent.VK_K) {
			DynamicBeat.game.release4();
		}
		else if(e.getKeyCode() == KeyEvent.VK_L) {
			DynamicBeat.game.release5();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SEMICOLON) {
			DynamicBeat.game.release6();
		}
		
		
	}

}
