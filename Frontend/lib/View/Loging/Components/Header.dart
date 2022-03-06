import 'package:flutter/material.dart';
import 'package:frontend/Global/Global.dart';

class Header extends StatelessWidget {
  const Header({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 250,
      width: double.infinity,
      child: CustomPaint(
        painter: HeaderPainter(),
      ),
    );
  }
}

class HeaderPainter extends CustomPainter {
  @override
  paint(Canvas canvas, Size size) {
    final paint = Paint();
    paint.color = purple;
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
