import 'package:flutter/material.dart';

//233,207,236,1

class HeaderLogin extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 250,
      width: double.infinity,
      child: CustomPaint(
        painter: HeaderLoginPainter(),
      ),
    );
  }
}

class HeaderLoginPainter extends CustomPainter {
  @override
  paint(Canvas canvas, Size size) {
    final paint = Paint();
    paint.color = Colors.purple;
    paint.style = PaintingStyle.fill;

    final path = Path();
    path.lineTo(0, size.height * 1.0);
    path.lineTo(size.width * 0.2, size.height * 0.8);
    path.lineTo(size.width, size.height * 1.0);
    path.lineTo(size.width, 0);
    canvas.drawPath(path, paint);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) => true;
}

class HeaderSignUp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 250,
      width: double.infinity,
      child: CustomPaint(
        painter: HeaderSignUpPainter(),
      ),
    );
  }
}

class HeaderSignUpPainter extends CustomPainter {
  @override
  paint(Canvas canvas, Size size) {
    final paint = Paint();
    paint.color = Colors.purple;
    paint.style = PaintingStyle.fill;

    final path = Path();
    path.lineTo(0, size.height * 1.0);
    path.lineTo(size.width * 0.8, size.height * 0.8);
    path.lineTo(size.width, size.height * 1.0);
    path.lineTo(size.width, 0);
    canvas.drawPath(path, paint);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) => true;
}
