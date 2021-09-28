#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <GL/glut.h>

typedef struct Screen {
    int width;
    int height;
} Screen;

static const Screen screen = {
    .width=1280,
    .height=720
};

typedef struct Window {
    int width;
    int height;
} Window;

static const Window window = {
    .width = 800,
    .height = 600
};

typedef struct Point {
    float x;
    float y;
} Point;

static Point point = {
    .x = 400,
    .y = 400,
};

void display (void);
void init_state (void);

Point world_to_normalized (Point* world_point);
Point normalized_to_device (Point* normalised_point);

int main (int argc, char* argv[])
{
    glutInit (&argc, argv);
    glutInitDisplayMode (GLUT_SINGLE | GLUT_RGBA);
    glutInitWindowSize (window.width, window.height);
    glutInitWindowPosition ((screen.width - window.width)/2,
                            (screen.height - window.height)/2);
    glutCreateWindow ("Coordinates system");
    init_state ();
    glutDisplayFunc (display);
    glutMainLoop ();

    return EXIT_SUCCESS;
}


void init_state (void)
{
    glClearColor (1.0f, 1.0f, 1.0f, 0.0f);
    glFlush ();
}

void display (void)
{
    glClear (GL_COLOR_BUFFER_BIT);
    glMatrixMode (GL_PROJECTION);
    glLoadIdentity ();
    // Orthogonal projection to world coordinates
    glOrtho (0, window.width, 0, window.height, -1, 1);

    glMatrixMode (GL_MODELVIEW);
    glLoadIdentity ();


    // Axis Coordinates
    glLineWidth (4);
    glBegin (GL_LINES);
        glColor3f (1.0f, 0.0f, 0.0f);
        glVertex2f (0, 0);
        glVertex2f (window.width, 0);

        glColor3f (0.0f, 1.0f, 0.0f);
        glVertex2f (0, 0);
        glVertex2f (0, window.width);
    glEnd ();
    
    // Point in device coordinates
    glPointSize (4);
    glBegin (GL_POINTS);
        glColor3f (1.0f, 0.0f, 0.0f);
        Point normalized_point = world_to_normalized (&point);
        Point device_point = normalized_to_device (&normalized_point);
        glVertex2i ((int) round (device_point.x), (int) round (device_point.y));
    glEnd ();

    glFlush ();
}

Point world_to_normalized (Point* world_point)
{
    Point normalized_point;
    normalized_point . x = (world_point -> x - 0)/(window . width - 0);
    normalized_point . y = (world_point -> y - 0)/(window . height - 0);
    return normalized_point;
}

Point normalized_to_device (Point* normalized_point)
{
    Point device_point;
    device_point . x = round (normalized_point -> x * (screen . width - 1));
    device_point . y = round (normalized_point -> y * (screen . height - 1));

    return device_point;
}
