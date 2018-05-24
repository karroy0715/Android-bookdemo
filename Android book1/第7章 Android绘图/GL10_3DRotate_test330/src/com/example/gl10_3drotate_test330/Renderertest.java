package com.example.gl10_3drotate_test330;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class Renderertest implements Renderer {
	private final GLCube cube;//立方体对象
	private Context context;
	private long startTime;//定义变量保存开始时间
	public Renderertest(Context context){
		cube=new GLCube();//实例化立方体对象
		this.context=context;
		startTime=System.currentTimeMillis();//为成员变量startTime赋初始值为当前时间
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		gl.glClearColor(0.7f, 0.9f, 0.9f, 1.0f);//设置窗体背景颜色
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//启用顶点坐标数组
		gl.glDisable(GL10.GL_DITHER);//关闭抗抖动
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);//设置系统对透视进行修正
		gl.glShadeModel(GL10.GL_SMOOTH);//设置阴影平滑模式
		gl.glEnable(GL10.GL_DEPTH_BITS);//启用深度测试
		gl.glDepthFunc(GL10.GL_LEQUAL);//设置深度测试的类型
		/**    应用纹理贴图     */
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//启用贴图坐标数组
		gl.glEnable(GL10.GL_TEXTURE_2D);//启用纹理贴图
		cube.loadTexture(gl,context,R.drawable.a0);
	}
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BITS);//清楚颜色缓存 深度缓存
		gl.glMatrixMode(GL10.GL_MODELVIEW);//设置使用模型矩阵进行交换
		gl.glLoadIdentity();//初始化单位矩阵
		//当时用GL_MODELVIEW模式时，必须设置视点也就是观察点
		GLU.gluLookAt(gl, 0, 0, -5.0f, 0f, 0f,0f, 0f, 1.0f, 0.0f);
		gl.glRotatef(1000, -0.1f, -0.1f, 0.05f);//旋转总坐标系
		/**-------旋转---------*/
		long elapsed=System.currentTimeMillis()-startTime;//计算逝去的时间
		gl.glRotatef(elapsed*(30f/1000f), 0, 1, 0);//在y轴上旋转30度
		gl.glRotatef(elapsed*(15f/1000f), 1, 0, 0);//在x轴上旋转15度
		
		cube.draw(gl);//绘制立方体
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);//设置OpenGL场景的大小
		float ratio=(float)width/height;//设置透视窗的宽度 高度比
		gl.glMatrixMode(GL10.GL_PROJECTION);//将当前矩阵模式设为投影矩阵
		gl.glLoadIdentity();//初始化单位矩阵
		GLU.gluPerspective(gl, 45.0f, ratio, 1, 100f);//设置透视视窗的空间大小
	}

}
