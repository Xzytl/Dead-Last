package com.deadlast.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.deadlast.game.DeadLast;
import com.deadlast.game.GameManager;

/**
 * Represents the player character.
 * @author Xzytl
 *
 */
public class Player extends Mob {
	
	private int stealthStat;
	
	private boolean isHidden;
	
	//private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("entities/player.png")));
	
	public Player(
			DeadLast game, int scoreValue, Sprite sprite, float bRadius,
			Vector2 initialPos, int healthStat, int speedStat, int strengthStat, int stealthStat
	) {
		super(game, scoreValue, sprite, bRadius, initialPos, healthStat, speedStat, strengthStat);
		this.stealthStat = stealthStat;
		this.isHidden = true;
	}
	
	public int getStealthStat() {
		return this.stealthStat;
	}
	
	@Override
	public void render(SpriteBatch batch) {
		Vector2 mousePos = GameManager.getInstance(this.game).getMouseWorldPos();
		double angle = Math.toDegrees(Math.atan2(mousePos.y - b2body.getPosition().y, mousePos.x - b2body.getPosition().x));
		this.setAngle(angle - 90);
		super.render(batch);
	}
	
	@Override
	public void defineBody() {
		BodyDef bDef = new BodyDef();
		bDef.type = BodyDef.BodyType.DynamicBody;
		bDef.position.set(initialPos);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(this.bRadius);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		
		b2body = world.createBody(bDef);
		b2body.createFixture(fDef);
		b2body.setUserData(this);

		shape.dispose();
	}

}
