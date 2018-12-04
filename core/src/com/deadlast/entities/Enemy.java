package com.deadlast.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.deadlast.game.DeadLast;
import com.deadlast.world.BodyFactory;

/**
 * A hostile mob that will attempt to damage the player.
 * @author Xzytl
 *
 */
public class Enemy extends Mob {
	
	private int detectionStat;
	
	private boolean knowsPlayerLocation = false;

	public Enemy(DeadLast game, int scoreValue, Sprite sprite, float bRadius, Vector2 initialPos,
			int healthStat, int speedStat, int strengthStat, int detectionStat) {
		super(game, scoreValue, sprite, bRadius, initialPos, healthStat, speedStat, strengthStat);
		this.detectionStat = detectionStat;
	}
	
	public int getDetectionStat() {
		return this.detectionStat;
	}
	
	@Override
	public void defineBody() {
		BodyDef bDef = new BodyDef();
		bDef.type = BodyDef.BodyType.DynamicBody;
		bDef.position.set(initialPos);
		
		// The physical body of the enemy
		FixtureDef fBodyDef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(this.bRadius);
		fBodyDef.shape = shape;
		
		// Create body and add fixtures
		b2body = world.createBody(bDef);
		b2body.createFixture(fBodyDef);
		
		BodyFactory bFactory = BodyFactory.getInstance(world);
		bFactory.makeConeSensor(b2body, 7, 70, 5f);
		bFactory.makeHearingSensor(b2body, this.bRadius + ((0.1f * (float)this.detectionStat)) + 0.5f);
		
		b2body.setUserData(this);

		shape.dispose();
		
		b2body.setLinearDamping(5.0f);
	}
	
	public void beginContact(Body body) {
		this.knowsPlayerLocation = true;
		
	}
	
	public void endContact(Body body) {
		this.knowsPlayerLocation = false;
	}
	
	@Override
	public void update() {
		if (knowsPlayerLocation) {
			Vector2 playerLoc = gameManager.getPlayerPos();
			double angle = Math.toDegrees(Math.atan2(playerLoc.y - b2body.getPosition().y, playerLoc.x - b2body.getPosition().x));
			this.setAngle(angle - 90);
		}
	}
	
	/**
	 * Utility for building Enemy instances.
	 * @author Xzytl
	 *
	 */
	public static class Builder {

		private DeadLast game;
		private int scoreValue;
		private Sprite sprite;
		private float bRadius;
		private Vector2 initialPos;
		private int healthStat;
		private int speedStat;
		private int strengthStat;
		private int detectionStat;
		
		public Builder setGame(DeadLast game) {
			this.game = game;
			return this;
		}
		
		public Builder setScoreValue(int scoreValue) {
			this.scoreValue = scoreValue;
			return this;
		}
		
		public Builder setSprite(Sprite sprite) {
			this.sprite = sprite;
			return this;
		}
		
		public Builder setBodyRadius(float bRadius) {
			this.bRadius = bRadius;
			return this;
		}
		
		public Builder setInitialPosition(Vector2 initialPos) {
			this.initialPos = initialPos;
			return this;
		}
		
		public Builder setHealthStat(int healthStat) {
			this.healthStat = healthStat;
			return this;
		}
		
		public Builder setSpeedStat(int speedStat) {
			this.speedStat = speedStat;
			return this;
		}
		
		public Builder setStrengthStat(int strengthStat) {
			this.strengthStat = strengthStat;
			return this;
		}
		
		public Builder setDetectionStat(int detectionStat) {
			this.detectionStat = detectionStat;
			return this;
		}
		
		/**
		 * Converts builder object into instance of Enemy
		 * @return an instance of Enemy with the provided parameters
		 * @throws IllegalArgumentException if required parameters are not provided
		 */
		public Enemy build() {
			// Ensure variables are not undefined
			// Note that primitive's are initialised as zero by default
			if (game == null) {
				throw new IllegalArgumentException("Invalid 'game' parameter");
			}
			if (sprite == null) {
				sprite = new Sprite(new Texture(Gdx.files.internal("entities/enemy.png")));
			}
			if (bRadius == 0) {
				bRadius = 0.5f;
			}
			if (initialPos == null) {
				throw new IllegalArgumentException("Invalid 'initialPos' parameter");
			}
			return new Enemy(
					game, scoreValue, sprite, bRadius, initialPos, healthStat, speedStat,
					strengthStat, detectionStat
			);
		}
	}

}
