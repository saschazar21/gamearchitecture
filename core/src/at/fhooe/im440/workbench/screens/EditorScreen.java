package at.fhooe.im440.workbench.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;

import at.fhooe.im440.workbench.Workbench;
import at.fhooe.im440.workbench.components.Editable;
import at.fhooe.im440.workbench.components.SpriteVisual;
import at.fhooe.im440.workbench.components.StaticPose;
import at.fhooe.im440.workbench.entities.TestEntity;
import at.fhooe.im440.workbench.helpers.Picasso;
import at.fhooe.im440.workbench.services.ServiceManager;
import at.fhooe.im440.workbench.services.AssetManager.AssetManager;
import at.fhooe.im440.workbench.services.CameraSystem.CameraSystem;
import at.fhooe.im440.workbench.services.EditorSystem.EditorSystem;
import at.fhooe.im440.workbench.services.EntityManager.Entity;

public class EditorScreen extends ScreenAdapter implements Screen {
	
	private Workbench workbench;
	private EditorSystem editorSystem;
	private Entity testEntity;
	
	public EditorScreen(Workbench workbench) {
		this.workbench = workbench;
		this.editorSystem = new EditorSystem();
		
		SpriteVisual spriteVisual = new SpriteVisual(ServiceManager.getService(AssetManager.class).getRegion("cog1")).width(1f).height(1f).offset(.5f, .5f).setOriginCenter();
		
		this.testEntity = new TestEntity().addComponents(new StaticPose(), new Editable(), spriteVisual);
	}
	
	@Override
	public void render(float delta) {
		Picasso.paintBackground(Picasso.WHITE);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {
		super.show();
		ServiceManager.addService(this.editorSystem);
		this.editorSystem.addEditable(this.testEntity);
		this.testEntity.addComponentsToManagers();
		this.editorSystem.subscribe();
	}

	@Override
	public void hide() {
		super.hide();
		ServiceManager.removeService(EditorSystem.class);
		this.editorSystem.removeEditable(this.testEntity);
		this.testEntity.removeComponentsFromManagers();
		this.editorSystem.unsubscribe();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}